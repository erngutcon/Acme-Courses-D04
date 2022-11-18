<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for teacher particular
- purposes.  The copyright owner does not offer teacher warranties or representations, nor do
- they accept teacher liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox readonly="true" code="teacher.follow-up.form.label.sequence-number" path="sequenceNumber"/>
	<acme:input-moment readonly="true" code="teacher.follow-up.form.label.instantiation-moment" path="instantiationMoment"/>
	<acme:input-textbox code="teacher.follow-up.form.label.message" path="message"/>
	<acme:input-url code="teacher.follow-up.form.label.hyperlink" path="hyperlink"/>
	
	<jstl:if test="${command == 'create' }">
		<acme:hidden-data path="helpRequestId"/>
		<acme:input-checkbox code="teacher.follow-up.form.label.confirmation" path="confirmation"/>
		<acme:submit code="teacher.follow-up.form.button.create" action="/teacher/follow-up/create"/>
	</jstl:if>
</acme:form>