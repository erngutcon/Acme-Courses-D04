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
	<acme:input-textbox code="teacher.help-request.form.label.ticker" path="ticker" readonly="true"/>
	<acme:input-textbox code="teacher.help-request.form.label.statement" path="statement" readonly="true"/>
	<acme:input-textbox code="teacher.help-request.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:input-textbox code="teacher.help-request.form.label.initDate" path="initDate" readonly="true"/>
	<acme:input-textbox code="teacher.help-request.form.label.finishDate" path="finishDate" readonly="true"/>
	<acme:input-textbox code="teacher.help-request.form.label.budget" path="budget" readonly="true"/>
	<acme:input-url code="teacher.help-request.form.label.hyperlink" path="hyperlink" readonly="true"/>
	<jstl:if test="${status!='PROPOSED'}">
	<acme:input-textbox code="teacher.help-request.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status=='PROPOSED'}">
		<acme:input-select code="teacher.help-request.form.label.status" path="status">
			<acme:input-option code="PROPOSED" value="PROPOSED" selected="true"/>
			<acme:input-option code="ACCEPTED" value="ACCEPTED"/>
			<acme:input-option code="DENIED" value="DENIED"/>
		</acme:input-select>
	</jstl:if>
	<jstl:if test="${command == 'show' && status == 'PROPOSED'}">
		<acme:submit code="teacher.help-request.form.button.update" action="/teacher/help-request/update"/>
	</jstl:if>
	<acme:button code="teacher.help-request.form.label.show-learner" action="/any/user-account/show?id=${learnerId}"/>
	
	<jstl:if test="${status=='ACCEPTED'}">
		<acme:button code="teacher.help-request.follow-up.form.button.create" action="/teacher/follow-up/create?helpRequestId=${id}" />
	</jstl:if>
</acme:form>
