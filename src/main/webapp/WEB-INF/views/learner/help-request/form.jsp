<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="learner.help-request.form.label.ticker" path="ticker" readonly="true"/>
	<jstl:if test="${command != 'create'}">
		<acme:input-textbox code="learner.help-request.form.label.creationMoment" path="creationMoment" readonly="true"/>
		<acme:input-textbox code="learner.help-request.form.label.initDate" path="initDate" readonly="true"/>
		<acme:input-textbox code="learner.help-request.form.label.finishDate" path="finishDate" readonly="true"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:input-textbox code="learner.help-request.form.label.initDate" path="initDate"/>
		<acme:input-textbox code="learner.help-request.form.label.finishDate" path="finishDate"/>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command,'create, show, update')}">
		<acme:input-textbox code="learner.help-request.form.label.budget" path="budget"/>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command,'delete, publish')}">
		<acme:input-textbox code="learner.help-request.form.label.budget" path="budget" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status != 'PROPOSED'}">
		<acme:input-textbox code="learner.help-request.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status == 'PROPOSED' && command != 'create'}">
		<acme:input-select code="learner.help-request.form.label.status" path="status">
			<acme:input-option code="PROPOSED" value="PROPOSED" selected="true"/>
			<acme:input-option code="ACCEPTED" value="ACCEPTED"/>
			<acme:input-option code="DENIED" value="DENIED"/>
		</acme:input-select>
	</jstl:if>
	<acme:input-textbox code="learner.help-request.form.label.statement" path="statement" readonly="true"/>
	<acme:input-url code="learner.help-request.form.label.hyperlink" path="hyperlink" readonly="true"/>
	
	
	<jstl:if test="${acme:anyOf(command,'create, show, update')}">
		<acme:input-textbox code="learner.help-request.form.label.budget" path="budget"/>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command,'delete, publish')}">
		<acme:input-textbox code="learner.help-request.form.label.budget" path="budget" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status != 'PROPOSED'}">
		<acme:input-textbox code="learner.help-request.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status == 'PROPOSED' && command != 'create'}">
		<acme:input-select code="learner.help-request.form.label.status" path="status">
			<acme:input-option code="PROPOSED" value="PROPOSED" selected="true"/>
			<acme:input-option code="ACCEPTED" value="ACCEPTED"/>
			<acme:input-option code="DENIED" value="DENIED"/>
		</acme:input-select>
	</jstl:if>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish') && published == false}">
			<acme:hidden-data path="initDate"/>
			<acme:hidden-data path="finishDate"/>
			<acme:button code="any.user-account.form.label.teacher" action="/any/user-account/show?id=${teacherId}"/>"/>
			<acme:submit code="learner.help-request.form.button.delete" action="/learner/help-request/delete"/>
			<acme:submit code="learner.help-request.form.button.update" action="/learner/help-request/update"/>
			<acme:submit code="learner.help-request.form.button.publish" action="/learner/help-request/publish"/>	
		</jstl:when>
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish') && published == true}">
			<acme:button code="any.user-account.form.label.teacher" action="/any/user-account/show?id=${teacherId}"/>"/>
		</jstl:when>
	</jstl:choose>
	<jstl:if test="${command == 'create'}">
		<acme:input-select code="learner.help-request.form.label.selectTeacher" path="teachers">
			<jstl:forEach items="${teachers}" var="teacher">
				<acme:input-option code="${teacher.userAccount.username}" value="${teacher.userAccount.username}" />
			</jstl:forEach>
		</acme:input-select>
		<acme:hidden-data path="status"/>
		<acme:submit code="learner.help-request.form.button.create" action="/learner/help-request/create"/>
	</jstl:if>
	
	
</acme:form>
