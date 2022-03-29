<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="restaurant">
    <h2>
        <c:if test="${restaurant['new']}">New </c:if> Restaurant
    </h2>
    <form:form modelAttribute="restaurant" class="form-horizontal" id="add-restaurant-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Usuario" name="user.username"/>
            <petclinic:inputField label="Password" name="user.password"/>
            <petclinic:inputField label="Name" name="name"/>
            <petclinic:inputField label="Email" name="email"/>
            <petclinic:inputField label="Telephone" name="telephone"/>
            <petclinic:inputField label="City" name="city"/>
            <petclinic:inputField label="Address" name="address"/>
            <petclinic:inputField label="Description" name="description"/>
            <petclinic:inputField label="Photo" name="photo"/>
            <petclinic:inputField label="Capacity" name="capacity"/>
            <petclinic:inputField label="Schedule" name="schedule"/>
            <select name="type" multiple style="background-color: white; border-radius:4px; width: 32.2%; margin-left: 10px; height: 120%">
            	<c:forEach items="${listaTipos}" var="mesa">
  					<option value="${mesa.name()}"><c:out value = "${mesa.name()}"/></option>
  				</c:forEach>
			</select>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${restaurant['new']}">
                        <button class="btn btn-default" type="submit">Add Restaurant</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
