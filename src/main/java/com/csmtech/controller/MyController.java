package com.csmtech.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csmtech.captcha.CaptchaServlet;
import com.csmtech.exporter.RegdExcelExporter;
import com.csmtech.exporter.RegdPDFExporter;
import com.csmtech.exporter.RegistrationPDFExporter;
import com.csmtech.model.Branch;
import com.csmtech.model.College;
import com.csmtech.model.Registration;
import com.csmtech.service.BranchService;
import com.csmtech.service.CollegeService;
import com.csmtech.service.RegistrationService;

import com.csmtech.utils.CommonFileUpload;
import com.csmtech.utils.Mail;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/assign")
public class MyController {

	@Autowired
	private CollegeService collegeService;

	@Autowired
	private BranchService branchService;

	@Autowired
	private RegistrationService registrationService;


	@GetMapping("/test")
	public String getData(Model model) {
		Registration registration = new Registration();
		CaptchaServlet captchaServlet = new CaptchaServlet();

		model.addAttribute("collegeList", collegeService.getAllCollege());
		model.addAttribute("branchesList", branchService.allBranchList());
		model.addAttribute("allRegistrationList", registrationService.getAllRegistration());
		return "admissionForm";
	}

	@GetMapping("/getBranchByCollegeId")
	public void getBranch(@RequestParam("collegeId") Integer collegeId, Model model, HttpServletResponse resp)
			throws IOException {
		PrintWriter pw = resp.getWriter();
		System.out.println(collegeId);
		List<Branch> branchList = branchService.allBranchList(collegeId);
		System.out.println("++++++++++++++++" + branchList);
		String t = "";
		t += "<option value='" + 0 + "'>" + "--select--" + "</option>";
		for (Branch x : branchList) {
			t += "<option value='" + x.getBranchId() + "'>" + x.getBranchName() + "</option>";
		}
		pw.print(t);
	}

	// searching the data
	@GetMapping("/search")
	public String getFilter(@RequestParam("collegeName") Integer cid, @RequestParam("branchName") Integer bid,
			Model model) {

		model.addAttribute("collegeList", collegeService.getAllCollege());
		model.addAttribute("branchesList", branchService.allBranchList());

		if (cid != 0 && bid == 0) {
			model.addAttribute("allRegistrationList", registrationService.findDetailsByCollegeName(cid));
		} else if (cid != 0 && bid != 0) {
			model.addAttribute("allRegistrationList",
					registrationService.findDetailsByCollegeNameAndBranchName(cid, bid));
		}

		else {
			model.addAttribute("allRegistrationList", registrationService.getAllRegistration());
		}

		return "admissionForm";
	}

	// saving the data
	@PostMapping("/saveRegistrationDetails")
	public String saveHousingDetails(@RequestParam(value = "registrationId", required = false) Integer registrationId,
			@RequestParam("applicantName") String name, @RequestParam("branchName") Branch branchId,
			@RequestParam("collegeName") College collegeId, @RequestParam("emailId") String email,
			@RequestParam("mobileNo") String mobileNo, @RequestParam("dob") String dateOfBirth,
			@RequestParam("gender") String gender, @RequestParam("imagePath") MultipartFile idProof,
			RedirectAttributes redirects, @RequestParam("captcha") String captcha, HttpServletRequest request)
			throws IOException {
		LocalDate today = LocalDate.now();
		System.out.println("today date is :" + today);
		LocalDate birthday = LocalDate.parse(dateOfBirth);
		System.out.println("the form date is:" + birthday);

		Period period = Period.between(birthday, today);
		int years = period.getYears();

		System.out.println("the age is:" + years);

		Registration registration = new Registration();

		if (registrationId != null)
			registration.setRegistrationId(registrationId);
		registration.setApplicantName(name);
		registration.setBranch(branchId);
		registration.setCollege(collegeId);
		registration.setEmailId(email);
		registration.setMobileNo(mobileNo);
		try {
			registration.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		registration.setAge(years);
		if (registration.getAge() < 12) {
			redirects.addFlashAttribute("Msg", "Age Graeter Than 12");

		}
		registration.setGender(gender);
		registration.setIsDelete("No");
		registration.setImageName(idProof.getOriginalFilename());
		String path = CommonFileUpload.singleFileUplaod(idProof, "idProof");
		System.out.println("Path--------->>" + path);
		registration.setImagePath(path);

		if (captcha.equals(request.getSession().getAttribute("captcha"))) {

			registrationService.saveAllRegistrationDetails(registration);
		}
		// Mail.sendEmailGmailTLS(registration);
		Mail.sendAttach(registration);
		return "redirect:./test";
	}

	// downloadFile Uploaded

	@GetMapping("/downloadFile")
	public void downloadFile(@RequestParam("registrationId") Integer registrationId, HttpServletResponse response)
			throws IOException {
		System.out.println("Inside Download File--------->>");
		Registration regd = registrationService.getDocumentDetailsByRegistrationId(registrationId);
		CommonFileUpload.downloadFile(response, regd.getImagePath());
	}

	@GetMapping("/deleteRegistration")
	public String deleteRegistrationApplicant(@RequestParam("registrationId") Integer registrationId) {
		registrationService.deleteRegistrationById(registrationId);
		return "redirect:./test";
	}

	@GetMapping(path = "/updateRegistration")
	public String updateStudentForm(Model model, @RequestParam("registrationId") Integer registrationId) {
		// model.addAttribute("allStudents", studentService.getAllStudents());
		System.out.println(registrationId);
		model.addAttribute("allRegistrationList", registrationService.getAllRegistration());
		model.addAttribute("regdd", registrationService.updateRegistrationById(registrationId));
		return "forward:/assign/test";
	}

	// Download all pdf for all

	@GetMapping("/Registration/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/excel");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<Registration> listRegisters = registrationService.listAll();

		RegdPDFExporter exporter = new RegdPDFExporter(listRegisters);
		exporter.export(response);

	}

	// download excel of all list

	@GetMapping("/Registration/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=regd_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<Registration> listRegisters = registrationService.listAll();

		RegdExcelExporter excelExporter = new RegdExcelExporter(listRegisters);

		excelExporter.export(response);
	}

	// download pdf by registrationId

	@GetMapping("/RegistrationById/export/pdf")
	public void exportToPDF(@RequestParam("registrationId") Integer registrationId, HttpServletResponse response)
			throws DocumentException, IOException {

		response.setContentType("application/excel");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=customer_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		Registration register = registrationService.downloadDetailsById(registrationId);

		RegistrationPDFExporter exporter = new RegistrationPDFExporter(register);
		exporter.export(response);

	}

}
