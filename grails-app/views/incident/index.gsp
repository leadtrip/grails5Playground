<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
    <ul>
        <g:each in="${actions}" var="anAction">
            <li><g:link action="${anAction}">${anAction}</g:link> </li>
        </g:each>
    </ul>

    <table class="table table-bordered">
        <tr>
            <th>Date</th>
            <th>Description</th>
        </tr>
        <g:each in="${allIncidents}" var="incident">
            <tr>
                <td>${incident.getIncidentDate()}</td>
                <td>${incident.getDescription()}</td>
            </tr>
        </g:each>
    </table>
</body>
</html>