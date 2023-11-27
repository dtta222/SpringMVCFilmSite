<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Films List</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f5f5f5;
            margin: 20px;
        }

        .films-list {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            margin-bottom: 10px;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .return-home {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="films-list">
        <c:choose>
            <c:when test="${!empty films}">
                <h2>Films List</h2>
                <ul>
                    <c:forEach var="film" items="${films}">
                        <li><a href="<c:url value='/GetFilmData.do' />?id=${film.id}">${film.title}</a></li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p>Sorry, no films were found for that search</p>
            </c:otherwise>
        </c:choose>

        <!-- Add a "Return Home" link -->
        <div class="return-home">
            <a href="<c:url value='/home.do' />">Return Home</a>
        </div>
    </div>
</body>
</html>
