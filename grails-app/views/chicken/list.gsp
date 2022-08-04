<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
    <table class="table table-bordered">
        <tr>
            <th>Name</th>
        </tr>
        <g:each in="${chickenList}" var="chicken">
            <tr>
                <td>${chicken.name}</td>
            </tr>
        </g:each>
    </table>
</body>
</html>