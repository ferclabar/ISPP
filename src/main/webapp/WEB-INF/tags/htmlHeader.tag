<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
commandfast :: a Spring Framework demonstration
--%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- The above 4 meta tags must come first in the head; any other head content must come after these tags --%>

    <spring:url value="/resources/images/favicon.png" var="favicon"/>
    <link rel="shortcut icon" type="image/x-icon" href="${favicon}">

    <title>Command-Fast</title>

    <%-- CSS generated from LESS --%>
    <spring:url value="/resources/css/commandfast.css" var="commandfastCss"/>
    <link href="${commandfastCss}" rel="stylesheet"/>


    <link href="/resources/css/commandfast.css" rel="stylesheet">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-icons.css" rel="stylesheet">
    
    <!--
    <spring:url value="/resources/css/bootstrap.min.css" var="bootCss"/>
    <link href="${bootCss}" rel="stylesheet"/>

    <spring:url value="/resources/css/bootstrap-icons.css" var="bootIconsCss"/>
    <link href="${bootIconsCss}" rel="stylesheet"/>
    -->

    <%-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries --%>
    <!--[if lt IE 9]>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js%22%3E></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js%22%3E></script>

    
    <![endif]-->

    <!-- Only datepicker is used -->
    <spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.css" var="jQueryUiCss"/>
    <link href="${jQueryUiCss}" rel="stylesheet"/>
   <spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.theme.min.css" var="jQueryUiThemeCss"/>
    <link href="${jQueryUiThemeCss}" rel="stylesheet"/>




    <script src="/resources/js/bootstrap.bundle.min.js"></script>
</head>
