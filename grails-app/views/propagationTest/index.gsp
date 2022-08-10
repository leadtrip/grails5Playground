<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
    Is transaction new?: ${params.isTransNew}
    <br>
    DB result: ${params.dbResult}
    <br><br>

    <g:link action="propagationNotSupported">Propagation not supported</g:link>
    <br>
    <g:link action="propagationNotSupportedControllerTransactional">Propagation not supported controller transactional</g:link>
    <br>
    <g:link action="propagationRequired">Propagation required</g:link>
    <br>
    <g:link action="propagationRequiredControllerTransactional">Propagation required controller transactional</g:link>
    <br>
    <g:link action="propagationSupports">Propagation supports</g:link>
    <br>
    <g:link action="propagationSupportsControllerTransactional">Propagation supports controller transactional</g:link>
    <br>
    <g:link action="propagationMandatory">Propagation mandatory</g:link>
    <br>
    <g:link action="propagationMandatoryControllerTransactional">Propagation mandatory controller transactional</g:link>
    <br>
    <g:link action="propagationRequiresNew">Propagation requires new</g:link>
    <br>
    <g:link action="propagationRequiresNewControllerTransactional">Propagation requires new controller transactional</g:link>
    <br>
    <g:link action="propagationNever">Propagation never</g:link>
    <br>
    <g:link action="propagationNeverControllerTransactional">Propagation never controller transactional</g:link>
    <br>
    <g:link action="propagationNested">Propagation nested</g:link>
    <br>
    <g:link action="propagationNestedControllerTransactional">Propagation nested controller transactional</g:link>
</body>
</html>