<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="menu">
    <h2>Menu</h2>
    <table class="table table-striped table-bordered">
            <tbody>
                <c:forEach items="${platos}" var="listaPlatos">
                	<tr>
						<td><p><c:out value="${listaPlatos.name}"/></p></td>
						<td><p><c:out value="${listaPlatos.cost} $"/></p></td>
						<td>
						<form:form modelAttribute="line" class="form-horizontal" id="add-line-form">
						
						<div style="position: relative; left: 25%;" class="form-group has-feedback">
							<input label="Plate" name="plate" value="${listaPlatos.id}" type="hidden"/>
							<c:if test="${!lines.isEmpty()}">
							<c:forEach items="${lines}" var="linea">
							<c:if test="${linea.plate.id == listaPlatos.id}">
							<p>${linea.quantity}</p>
							</c:if>
							</c:forEach>
							</c:if>
							<input label="Cantidad" style="width: 100px" name="quantity" type="number" max="50" min="0"/>
							<input label="Comanda" name="command" value="${id_commanda}" type="hidden"/>
							<button style="margin-left: 10px" class="btn btn-default" type="submit"><span>Pedir</span></button>
        				</div>
    					</form:form>
    					</td>
				</tr>
    			</c:forEach>
           </tbody>
           <spring:url value="/carta/{id_comanda}/ticket" var="url">
           		<spring:param name="id_comanda" value="${id_commanda}"/>
           </spring:url>
    </table>																																			
    <a class="btn btn-default" href="${fn:escapeXml(url)}"><span>Finalizar pedido</span></a>
</petclinic:layout>