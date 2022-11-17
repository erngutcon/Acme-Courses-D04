<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<acme:form>
	<acme:input-textbox
		code="administrator.configuration.configuration.form.label.currency"
		path="currency" />
	<acme:input-textbox
		code="administrator.configuration.configuration.form.label.acceptedCurrencies"
		path="acceptedCurrencies" />

	<acme:message
		code="administrator.configuration.configuration.form.label.spamRecords" />
	<table class="table table-sm">
		<tr>
			<th><acme:message
					code="administrator.configuration.configuration.form.label.spamRecords.term" /></th>
			<th><acme:message
					code="administrator.configuration.configuration.form.label.spamRecords.weight" /></th>
			<th><acme:message
					code="administrator.configuration.configuration.form.label.spamRecords.booster" /></th>
		</tr>
		<jstl:forEach items="${spamRecords}" var="record">
			<tr>
				<td><acme:print value="${record.term}" /></td>
				<td><acme:print value="${record.weight}" /></td>
				<td><acme:print value="${record.booster}" /></td>
			</tr>
		</jstl:forEach>
	</table>

	<acme:input-textbox
		code="administrator.configuration.configuration.form.label.spamThreshold"
		path="spamThreshold" />
	<acme:input-textbox
		code="administrator.configuration.configuration.form.label.spamBoosterFactor"
		path="spamBoosterFactor" />
</acme:form>