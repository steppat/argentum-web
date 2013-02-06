<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<html>
<head>
	<title>Argentum Web</title>
</head>
<body>
<form:form  action="validaForm" method="post" commandName="form">

<h2>Titulo: </h2>
    <form:errors path="titulo" cssClass="error" element="div" />
	<form:input path="titulo"/>

<h2>Indicadores: </h2>

<form:select path="indicador">
	<form:option value="abertura">Abertura</form:option>
	<form:option value="fechamento">Fechamento</form:option>
	<form:option value="minimo">Mínimo</form:option>
	<form:option value="maximo">Máximo</form:option>
</form:select>

<h2>Medias: </h2>

<form:select path="media">
	<form:option value="">---</form:option>
	<form:option value="simples">simples</form:option>
	<form:option value="ponderada">ponderada</form:option>
</form:select>

	Dias: 
	<form:errors path="dias" cssClass="error" element="div" />
	<form:input path="dias"  />
		
<h2>Data: </h2>
(Max dois meses atras:)
    <form:input path="data"/> (dd/MM/yyyy)
	
	<br /> <br />

	<input type="submit"  value="Enviar"/>
	 
</form:form>

</body>
</html>
