<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Records</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 20px;
        }
        h2, h3 {
            color: #333;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        form {
            margin: 10px auto;
            display: inline-block;
            background: white;
            padding: 10px 15px;
            border-radius: 8px;
            box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
        }
        input[type="text"] {
            padding: 8px;
            width: 150px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-right: 5px;
        }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 8px 12px;
            cursor: pointer;
            border-radius: 5px;
            font-size: 14px;
        }
        button:hover {
            background-color: #0056b3;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
        }
        a:hover {
            background: #218838;
        }
    </style>
</head>
<body>

    <h2>All Students</h2>

    <%
        // Retrieve the students list from request scope
        java.util.List<com.example.StudentDetails.StudentDetails.Entity.Student> students =
            (java.util.List<com.example.StudentDetails.StudentDetails.Entity.Student>) request.getAttribute("students");

        if (students != null && !students.isEmpty()) {
    %>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Year</th>
                <th>Department</th>
            </tr>
            <%
                for (com.example.StudentDetails.StudentDetails.Entity.Student student : students) {
            %>
            <tr>
                <td><%= student.getId() %></td>
                <td><%= student.getName() %></td>
                <td><%= student.getYear() %></td>
                <td><%= student.getDepartment() %></td>
            </tr>
            <%
                }
            %>
        </table>
    <%
        } else {
    %>
        <p style="color: red;">No student records found.</p>
    <%
        }
    %>

   <h3>Filter by Year</h3>
<form id="yearForm" onsubmit="submitYearForm(event)">
    <input type="text" id="yearInput" placeholder="Enter Year">
    <button type="submit">Search</button>
</form>

<h3>Filter by Department</h3>
<form id="deptForm" onsubmit="submitDeptForm(event)">
    <input type="text" id="deptInput" placeholder="Enter Department">
    <button type="submit">Search</button>
</form>

<script>
    function submitYearForm(event) {
        event.preventDefault();  // Prevents normal form submission
        let year = document.getElementById("yearInput").value;
        if (year) {
            window.location.href = "/students/year/" + year;  // Redirect to Path Variable URL
        } else {
            alert("Please enter a year!");
        }
    }

    function submitDeptForm(event) {
        event.preventDefault();  // Prevents normal form submission
        let department = document.getElementById("deptInput").value;
        if (department) {
            window.location.href = "/students/department/" + department;  // Redirect to Path Variable URL
        } else {
            alert("Please enter a department!");
        }
    }
</script>


    <br>
    <a href="/">Go Back</a>

</body>
</html>
