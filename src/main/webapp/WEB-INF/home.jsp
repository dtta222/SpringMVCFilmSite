<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>David's Film Database</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f5f5f5;
            margin: 20px;
            text-align: center;
        }

        .welcome-message {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .search-form {
            margin-top: 20px;
        }

        label {
            font-weight: bold;
            margin-right: 5px;
        }

        input[type="text"] {
            padding: 8px;
            margin-right: 10px;
        }

        input[type="submit"] {
            padding: 8px 15px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="welcome-message">
        <h1>Welcome to David's Film Thingy</h1>

        <!-- Search by Index # Form -->
        <div class="search-form">
            <h3>Search by Index #:</h3>
            <!-- Form to search for a film by its ID -->
            <form action="GetFilmData.do" method="get">
                <label for="id">ID#:</label>
                <!-- Input field for the film ID -->
                <input type="text" name="id">
                <!-- Submit button to initiate the search -->
                <input type="submit" value="Search">
            </form>
        </div>

        <!-- Search by Description Form -->
        <div class="search-form">
            <h3>Search by Description:</h3>
            <!-- Form to search for films by a keyword in the description -->
            <form action="GetFilmsData.do" method="get">
                <label for="keyword">Keyword:</label>
                <!-- Input field for the keyword -->
                <input type="text" name="keyword">
                <!-- Submit button to initiate the search -->
                <input type="submit" value="Search">
            </form>
        </div>
    </div>
</body>
</html>
