
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<petclinic:layout pageName="detalles-restaurante">
	
<div class="container py-5">
	<div class="row min-vh-50 align-items-center">
		<div class="col-md-12 text-center">
			<div class="lc-block mb-4">
				<div editable="rich">
					<h2 class="display-3 fw-bolder" style="color: #bd8859;"><c:out value = "${detallesRestaurante.name}"/></h2>
					<p><c:out value = "${detallesRestaurante.description}"/></p>
					<h2><button type="button" class="btn-default">Ver carta</button></h2>
				</div>

				<div class="col-6">
					<spring:url value="/restaurante/{id}/detalles/carta" var="menUrl">
							<spring:param name="id" value="${detallesRestaurante.id}"/>
					</spring:url>
					<a class="btn-default" href="${fn:escapeXml(menUrl)}">Ver carta</a>
				</div>

			</div><!-- /lc-block -->
			<div class="lc-block border-top col-md-6 offset-md-3" style="border-top: 2px solid #b98057 !important;">
				<div editable="rich">
					<h2 class="fw-bolder"><br></h2>
				</div>
			</div>
			<div class="lc-block mb-4" >
				<div editable="rich">
					<h2 class="fw-bolder" style="
                    display: flex;
                    justify-content
                : center;
                    width: 100%;
					color: #8d6542;
                ">Working Hours</h2>
				</div>
			</div>
			<div class="lc-block">
				<div editable="rich">
					<p><c:out value = "${detallesRestaurante.schedule}"/></p>
				</div>
			</div><!-- /lc-block -->
		</div><!-- /col -->
	</div>
</div>

<div class="container">
	<div class="row align-items-center">
		<div class="col-md-6">
			<div class="lc-block mb-4">
				<div class="ratio ratio-4x3 min-vh-50" lc-helper="gmap-embed">
					<iframe src="https://maps.google.com/maps?q=<c:out value = "${detallesRestaurante.address}"/>&amp;t=m&amp;z=8&amp;output=embed&amp;iwloc=near"></iframe>
				</div>
			</div><!-- /lc-block -->
		</div><!-- /col -->
		<div class="col-md-6 px-5">
			<div class="lc-block mb-4">
				<div editable="rich">
					<h2 class="display-6 fw-bolder" style="color: #8d6542;">Find us!<p></p>
						<p></p>
					</h2>
				</div>
			</div>
			<div class="lc-block mb-4">
				<div editable="rich">
					<p class="lead"><c:out value = "${detallesRestaurante.address}"/></p>
					<p class="lead"><c:out value = "${detallesRestaurante.telephone}"/></p>
					<p class="lead"><c:out value = "${detallesRestaurante.email}"/></p>

				</div>
			</div>
			
		</div><!-- /col -->
	</div>
</div>
</petclinic:layout>