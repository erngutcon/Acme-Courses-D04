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
	<acme:input-textbox code="learner.help-request.form.label.statement" path="statement" readonly="true"/>
	<acme:input-textbox code="learner.help-request.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:input-textbox code="learner.help-request.form.label.initDate" path="initDate" readonly="true"/>
	<acme:input-textbox code="learner.help-request.form.label.finishDate" path="finishDate" readonly="true"/>
	<acme:input-textbox code="learner.help-request.form.label.status" path="status" readonly="true"/>
	<acme:input-textbox code="learner.help-request.form.label.budget" path="budget" readonly="true"/>
	<acme:input-url code="learner.help-request.form.label.hyperlink" path="hyperlink" readonly="true"/>
	
	<jstl:if test="${status=='ACCEPTED'}">
		<acme:button code="learner.help-request.follow-up.form.button.create" action="/learner/follow-up/create?helpRequestId=${id}" />
	</jstl:if>
	
	<acme:button code="any.user-account.form.label.teacher" action="/any/user-account/show?id=${teacherId}"/>
</acme:form>
