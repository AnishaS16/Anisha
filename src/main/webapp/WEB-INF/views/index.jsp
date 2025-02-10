<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 20px;
        }

        h2 {
            color: #333;
        }

        form {
            background: white;
            padding: 20px;
            margin: 10px auto;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
        }

        label {
            font-weight: bold;
        }

        input[type="file"] {
            margin: 10px 0;
            padding: 5px;
        }

        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 5px;
            font-size: 16px;
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
    <h2>Upload Student Data</h2>
    
    <!-- Upload CSV File -->
    <form action="/students/upload/csv" method="POST" enctype="multipart/form-data">
        <label>Select CSV File:</label>
        <input type="file" name="file" accept=".csv">
        <br>
        <button type="submit">Upload CSV</button>
    </form>

    <br>

    <!-- Upload Excel File -->
    <form action="/students/upload/excel" method="POST" enctype="multipart/form-data">
        <label>Select Excel File:</label>
        <input type="file" name="file" accept=".xlsx">
        <br>
        <button type="submit">Upload Excel</button>
    </form>

    <br>

    <!-- Link to View Students -->
    <a href="/students/view">View Student Records</a>
</body>
</html>
