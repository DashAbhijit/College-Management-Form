<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.min.css">
<link
	rel="https://cdnjs.datatables/1.12.1/css/dataTables.bootstrap4.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap4.min.js"></script>
<!-- ......................for nav tab bar.............................................. -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">
<!-- ................................................................................................. -->

<!-- Include Bootstrap DateTimePicker CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css"
	rel="stylesheet">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>
<!--  -------------------------------------    -->


<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">
</head>
<body>

	<div class="container mt-4">
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#home">Add</a></li>
			<li><a data-toggle="tab" href="#menu1">View</a></li>

		</ul>

		<div class="tab-content">
			<div id="home" class="tab-pane fade in active">


				<div class="container mt-4">
					<a href="./Registration/export/pdf"><i
						class="fas fa-file-pdf fa-3x" style="color: red"
						aria-hidden="true"></i></a> <a href="./Registration/export/excel"><i
						class="fas fa-file-excel fa-3x" style="color: green"
						aria-hidden="true"></i></a>
					<p align="right">
						<span class="text-danger">*</span> indicates mandatory fields
					</p>
					<form action="./saveRegistrationDetails" method="post"
						enctype="multipart/form-data"
						onsubmit="return AdmissionFormValidation()">

						<input type="hidden" name="registrationId" id="registrationId"
							value="${regdd.registrationId}">
						<marquee bgcolor="lightblue" class="display-3">College
							Admission Form</marquee>
						<div class="row">
							<div class="form-group col-md-4 offset-md-2">
								<label>College Name :<span class="text-danger">*</span></label>
								<select onchange="college_branch()" id="collegeId"
									name="collegeName" class="form-control"
									onclick="clgvalidation()" required="required">
									<option value="select">-select-</option>
									<c:forEach items="${collegeList}" var="college">

										<option value='${college.collegeId}'
											<c:if test="${college.collegeId eq regdd.college.collegeId}">selected='selected'</c:if>>${college.collegeName}</option>
									</c:forEach>
								</select> <span id="projectIdEr" class="text-danger font-weight-bold"></span>
							</div>
							<div class="form-group col-md-4">
								<label>Branch Name :<span class="text-danger">*</span></label>
								<c:if test="${regdd ne null}">
									<select id="branchId" name="branchName" class="form-control"
										onclick="branchvalidation()" required="required">
										<option value="select">-select-</option>
										<c:forEach items="${regdd.college.branch}" var="branch">
											<option value='${branch.branchId}'
												<c:if test="${branch.branchId eq regdd.branch.branchId}">selected='selected'</c:if>>${branch.branchName}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${regdd eq null}">
									<select id="branchId" name="branchName" class="form-control"
										onclick="branchvalidation()">
										<option value="${branch.branchName}">-select-</option>
									</select>
								</c:if>
							</div>
						</div>
						<div class="container"
							style="margin-top: 25px; border: 1px solid black;">
							<h4>Applicant Details</h4>
							<div class="row">
								<div class="form-group col-md-4">
									<label>Applicant Name :<span class="text-danger">*</span></label>
									<input type="text" name="applicantName" id="applicantName"
										class="form-control" value="${regdd.applicantName}"> <span
										id="applicantNameEr" class="text-danger font-weight-bold"></span>
								</div>


								<div class="form-group col-md-4">
									<label>Email :<span class="text-danger">*</span></label> <input
										type="text" name="emailId" id="email" class="form-control"
										value="${regdd.emailId}"> <span id="emailEr"
										class="text-danger font-weight-bold"></span>
								</div>
								<div class="form-group col-md-4">
									<label>Mobile No:<span class="text-danger">*</span></label> <input
										type="number" name="mobileNo" id="mobileNo"
										class="form-control" value="${regdd.mobileNo}"
										onclick="validate()"> <span id="mobileNoEr"
										class="text-danger font-weight-bold"></span>
								</div>
							</div>

							<div class="row">

								<div class="form-group col-md-4">
									<label>Date of Birth:<span class="text-danger">*</span></label>
									<input type="Date" name="dob" id="dob" class="form-control"
										value="<f:formatDate pattern="yyyy-MM-dd" type="date" value="${regdd.dob}"/>">
									<span id="dobEr" class="text-danger font-weight-bold"></span>
								</div>
								<div class="form-group col-md-4">
									<label>Gender :<span class="text-danger">*</span></label> <br>
									<input type="radio" name="gender" id="gentype1" value="Female"
										<c:if test="${regdd.gender eq 'Female' }" >checked="checked"</c:if>>Female
									<input type="radio" name="gender" id="gentype2" value="Male"
										<c:if test="${regdd.gender eq 'Male' }">checked="checked"</c:if>>Male
									<br>
									<span id="genderEr" class="text-danger font-weight-bold"></span>
								</div>
								<div class="form-group col-md-4">
									<label>Upload Photo :<span class="text-danger">*</span></label>
									<input type="file" name="imagePath" id="imageId"
										value="${regdd.imagePath}" class="form-control">
								</div>
							</div>
							<div class=" d-flex align-item-center justify-content-center mt-4">

								<div class="form-group col-md-4">
									<label>Captcha :</label>
									 <img alt="captcha" src="${path}/captcha-servlet">
								</div>
							</div>
							<div class=" d-flex align-item-center justify-content-center">
								<div class="form-group col-md-4">
									<label>Enter Capcha Here :<span class="text-danger">*</span></label> <input
										type="text" name="captcha" id="captcha" class="form-control">
									<br>
									<span id="captchaEr" class="text-danger font-weight-bold"></span>
								</div>
							</div>
							<div class="mb-4" align="center">
								<input type="submit" id="tabOneSubmit"
									onclick="return confirm('Are you want to Save your Details');"
									<c:choose><c:when test="${regd eq null }"> value="Submit" </c:when><c:otherwise>value="Update"</c:otherwise></c:choose>
									class="btn btn-success btn-lg">&nbsp;&nbsp; <input
									type="reset" value="Reset" class="btn btn-danger btn-lg">

							</div>
						</div>

					</form>



				</div>
			</div>
			<div id="menu1" class="tab-pane fade">
				<h2 class="display-4 text-danger" align="center">Registration
					Details</h2>
				<form action="./search">
					<div class="container mt-5"
						style="margin-top: 25px; border: 1px solid black;">
						<h5>Filter</h5>
						<div class="form-group col-md-4">
							<label>College Name :<span class="text-danger">*</span></label> <select
								id="CollegeId" name="collegeName" class="form-control">
								<option value="0">-select-</option>
								<c:forEach items="${collegeList}" var="college">
									<option value='${college.collegeId}'>${college.collegeName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-md-4">
							<label>Branch Name:<span class="text-danger">*</span></label> <select
								id="branchId" name="branchName" class="form-control"
								onclick="validateProperty()">
								<option value="0">-select-</option>
								<c:forEach items="${branchesList}" var="branch">
									<option value='${branch.branchId}'>${branch.branchName}</option>
								</c:forEach>
							</select>
						</div>
						<div>

							<input type="submit" value="Search">
						</div>
					</div>



				</form>
				<a
					href="./Registration/export/excel?collegeId=${college.collegeId}&branchId=${branch.branchId}"><i
					class="fas fa-file-excel fa-3x" style="color: green"
					aria-hidden="true"></i></a>


				<div class="container mt-5">
					<table class="table table-stripped" id="RegistrationTable"
						border="1">
						<thead>
							<tr>
								<th>Sl#</th>
								<th>Name</th>
								<th>Email</th>
								<th>Age</th>
								<th>Mobile No</th>
								<th>Image</th>
								<th>College Name</th>
								<th>BranchName</th>
								<th>Fees</th>
								<th>Download pdf</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>


							<c:forEach items="${allRegistrationList}" var="register"
								varStatus="count">
								<tr>
									<td>${count.count}</td>
									<td>${register.applicantName}</td>
									<td>${register.emailId}</td>
									<td>${register.age}</td>
									<td>${register.mobileNo}</td>
									<td><a
										href="./downloadFile?registrationId=${register.registrationId}">${register.imageName}</a></td>
									<td>${register.college.collegeName}</td>
									<td>${register.branch.branchName}</td>
									<td>&#x20B9 ${register.branch.fees}</td>
									<td><a
										href="./RegistrationById/export/pdf?registrationId=${register.registrationId}"><i
											class="fas fa-file-pdf" style="color: red; font-size: 30px"
											aria-hidden="true"></i></a></td>
									<td><a
										onclick="return confirm('Are you want to delete your Details');"
										href="./deleteRegistration?registrationId=${register.registrationId}"><i
											class="fas fa-trash text-danger" style="font-size: 30px"></i></a>&nbsp;&nbsp;
										<a
										onclick="return confirm('Are you want to Update your Details');"
										href="./updateRegistration?registrationId=${register.registrationId}"><i
											class="fas fa-pen-nib text-warning" style="font-size: 30px"></i></a></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

				</div>
			</div>
		</div>
	</div>



</body>

<script type="text/javascript">
	function college_branch() {
		var college = $("#collegeId").val();
		$.ajax({
			type : "GET",
			url : "./getBranchByCollegeId",
			data : "collegeId=" + college,
			success : function(response) {
				$('#branchId').html(response);
			}
		});

	}

	function AdmissionFormValidation() {

		var vApplicantName = document.getElementById("applicantName").value;
		var vemail = document.getElementById("email").value;
		var vmobileNo = document.getElementById("mobileNo").value;
		var vFemale = document.getElementById("gentype1").value;
		var vMale = document.getElementById("gentype2").value;
		var vcaptcha = document.getElementById("captcha").value;
		var applicantCheck = /^[A-Za-z. ]{3,}$/;
		var emailCheck = /^[a-z0-9]{3,}@[a-z]{3,}[.]{1}[a-z.]{2,6}$/;
		var mobileCheck = /^[6789][0-9]{9}$/;

		var dob1 = document.getElementById("dob").value;
		var dob = new Date(dob1);
		//calculate month difference from current date in time  
		var month_diff = Date.now() - dob.getTime();

		//convert the calculated difference in date format  
		var age_dt = new Date(month_diff);

		//extract year from date      
		var year = age_dt.getUTCFullYear();

		//now calculate the age of the user  
		var age = Math.abs(year - 1970);

		if (applicantCheck.test(vApplicantName)) {
			document.getElementById("applicantNameEr").innerHTML = " ";
		} else {
			document.getElementById("applicantNameEr").innerHTML = " ** Applicant name must contains 3 character and only alphabets ";
			return false;
		}
		if (emailCheck.test(vemail)) {
			document.getElementById("emailEr").innerHTML = " ";
		} else {
			document.getElementById("emailEr").innerHTML = " ** Please provide valid email ";
			return false;
		}
		if (mobileCheck.test(vmobileNo)) {
			document.getElementById("mobileNoEr").innerHTML = " ";
		} else {
			document.getElementById("mobileNoEr").innerHTML = " ** Please provide valid mobileNo and ist should be indian standard ";
			return false;
		}

		if (age > 18) {
			document.getElementById("dobEr").innerHTML = " ";
		} else {
			document.getElementById("dobEr").innerHTML = " **please provide date of birth and age must be greater than 18 ";
			return false;
		}
		if (vcaptcha.test(null)) {
			document.getElementById("captchaEr").innerHTML = " ** please enter captcha and it should be correct";
		} else {
			document.getElementById("captchaEr").innerHTML = " ";
			return false;
		}

	}
</script>
<script type="text/javascript">
	function clgvalidation() {
		var collegeselector = document.getElementById("collegeId").value;
		if (collegeselector == "Select") {
			alert("Please select College Name");
			return false;
		}

	}
	function branchvalidation() {
		var branchselector = document.getElementById("branchId").value;
		if (branchselector == "Select") {
			alert("Please select Branch Name");
			return false;
		}

	}
</script>
<script>
	$('submit').click(function() {
		alert("hello");
	})
	$('#dob').datetimepicker({
		format : 'YYYY/MM/DD hh:mm a'
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#RegistrationTable').DataTable();
	});
</script>

</html>