<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="res/mc.css" type="text/css" title="cse4413" media="screen, print" />
		<meta charset="ISO-8859-1">
		<title>EECS4413</title>
	</head>
	<body>
		<nav>
			<header>Student Loan Application</header>
			<a href="">Home</a>
			<a href="">About</a>
		</nav>
		<br>
		<form action="UI.html" method="GET" class="resultForm">
			<fieldset>
				<legend>Calculator</legend>
				<article><strong>Monthly payments:
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</strong> ${payment} <br></article>
				<section>Calculation used principal=${principal}, interest=${interest} and period=${period}</section><br/>
				<button type="submit" name="restart" value="true">Re-compute</button>
			</fieldset>
		</form>
		<br><br><br>
		<center><img src="./res/logo.png" width="110" height="20"></center>
		<br>
		<aside>York University</aside>
		<br>
		<footer>Copyright EECS 4413A-All rights reserved</footer>
	</body>
</html>