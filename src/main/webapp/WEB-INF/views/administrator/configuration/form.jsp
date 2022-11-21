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
		
		<acme:input-textbox
		code="administrator.configuration.configuration.form.label.spamThreshold"
		path="spamThreshold" />
	<acme:input-textbox
		code="administrator.configuration.configuration.form.label.spamBoosterFactor"
		path="spamBoosterFactor" />

<fieldset>
		<legend>
	<acme:message
		code="administrator.configuration.configuration.form.label.spamRecords" />
		</legend>
	<table class="table table-sm">
	<caption>Administrator Configuration</caption>
		<tr>
			<th scope="row"><acme:message
					code="administrator.configuration.configuration.form.label.spamRecords.term" /></th>
			<th scope="row"><acme:message
					code="administrator.configuration.configuration.form.label.spamRecords.weight" /></th>
			<th scope="row"><acme:message
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
	</fieldset>
	
	<fieldset>
		<legend>
			<acme:message
				code="administrator.configuration.configuration.form.label.newSpamRecord" />
		</legend>
		<acme:input-textbox
			code="administrator.configuration.configuration.form.label.spamRecords.term" path="newTerm" />
		<acme:input-double
			code="administrator.configuration.configuration.form.label.spamRecords.weight" path="newWeight" />
		<acme:input-textbox
			code="administrator.configuration.configuration.form.label.spamRecords.booster" path="newBoosterTerm" />
	</fieldset>

	
		
	<acme:button  test="${command == 'show'}" code="administrator.configuration.configuration.form.button.update" action="/administrator/configuration/update"/>
	<acme:submit test="${command == 'update'}" code="administrator.configuration.configuration.form.button.submit" action="/administrator/configuration/update"/>
</acme:form>