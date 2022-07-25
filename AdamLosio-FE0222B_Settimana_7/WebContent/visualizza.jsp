

<!-- questa jsp gestisce la visualizzazione dei contatti si se faccio la get all 
sia se faccio la ricerca per nome o  per cognome -->


<%@page import="it.data.Telefono"%>
<%@page import="java.util.List"%>
<%@page import="it.data.Contatto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualizza</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
</head>

<body class="bg-dark">
<%!@SuppressWarnings("unchecked")%>
<%
List<Contatto> contatti=(List<Contatto>)session.getAttribute("contatti"); 
String azione=(String)session.getAttribute("azione"); 
Contatto contatto=(Contatto)session.getAttribute("contatto");
%>
<div class="container justify-content-center mt-4">
<%
if(azione.equals("cerca")){
%>

<table class="table bg-dark text-light table-bordered border-primary ">
  <thead>
    <tr>
      <th scope="col">Nome:</th>
      <th scope="col">Cognome:</th>
      <th scope="col">Email:</th>
       <th scope="col">Numero/i:</th>
    </tr>
  </thead>
  <tbody>
    <tr>
     <td> <%=contatto.getNome()%></td>
<td> <%=contatto.getCognome()%></td>
 <td><%=contatto.getEmail()%></td>    
 <td><%
     for(Telefono numero:contatto.getNumTelefoni()){
     %>
	 <%=numero.getNumTelefono()%>
	 <%
	 if(contatto.getNumTelefoni().size()>1){
	 %> ;<br> <%
 }
 %>
<%
}
%></td> 
    </tr>
  
  </tbody>
</table>


<%
}else if(azione.equals("visualizza")){
%>

<table class="table bg-dark text-light border-primary table-bordered">
  <thead>
    <tr>
      <th scope="col">Nome: </th>
      <th scope="col">Cognome: </th>
      <th scope="col">Email: </th>
       <th scope="col">Numero/i di Telefono:</th>
    </tr>
  </thead>
  <tbody>
  <%
  for(Contatto cont:contatti){
  %>
    <tr>
     <td> <%=cont.getNome()%></td>
<td> <%=cont.getCognome()%></td>
 <td><%=cont.getEmail()%></td>    
 <td><%
     for(Telefono numero:cont.getNumTelefoni()){
     %>
	 <%=numero.getNumTelefono()%>
	 <%if(cont.getNumTelefoni().size()>1){%> ;<br> <% } %>
<% } %></td> 
    </tr>
    <%}%>
    </tbody>
</table>

 <%  }%>
</div>
  

<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
		crossorigin="anonymous"></script>
</body>
</html>