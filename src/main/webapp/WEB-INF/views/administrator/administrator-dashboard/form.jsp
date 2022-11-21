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



<table class="table table-sm">
	<caption>Administrator Dashboard</caption>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-of-theory-tutorials" />
		</th>
		<td><acme:print value="${totalNumberOfTheoryTutorials}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-cost-of-theory-tutorials" /></th>
		<jstl:forEach var="prices" items="${averageCostOfTheoryTutorials}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-cost-of-theory-tutorials" /></th>
		<jstl:forEach var="prices" items="${deviationCostOfTheoryTutorials}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-cost-of-theory-tutorials" /></th>
		<jstl:forEach var="prices" items="${minimumCostOfTheoryTutorials}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-cost-of-theory-tutorials" /></th>
		<jstl:forEach var="prices" items="${maximumCostOfTheoryTutorials}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-of-lab-tutorials" />
		</th>
		<td><acme:print value="${totalNumberOfLabTutorials}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-cost-of-lab-tutorials" /></th>
		<jstl:forEach var="prices" items="${averageCostOfLabTutorials}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-cost-of-lab-tutorials" /></th>
		<jstl:forEach var="prices" items="${deviationCostOfLabTutorials}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-cost-of-lab-tutorials" /></th>
		<jstl:forEach var="prices" items="${minimumCostOfLabTutorials}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-cost-of-lab-tutorials" /></th>
		<jstl:forEach var="prices" items="${maximumCostOfLabTutorials}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-of-proposed-help-requests" /></th>
		<td><acme:print value="${totalNumberOfProposedHelpRequests}" /></td>
	</tr>
		<tr>
	<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-of-accepted-help-requests" /></th>
		<td><acme:print value="${totalNumberOfAcceptedHelpRequests}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-of-denied-help-requests" /></th>
		<td><acme:print value="${totalNumberOfDeniedHelpRequests}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-budget-of-proposed-help-requests" /></th>
		<jstl:forEach var="prices" items="${averageBudgetOfProposedHelpRequests}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-budget-of-proposed-help-requests" /></th>
		<jstl:forEach var="prices" items="${deviationBudgetOfProposedHelpRequests}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-budget-of-proposed-help-requests" /></th>
		<jstl:forEach var="prices" items="${minimumBudgetOfProposedHelpRequests}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-budget-of-proposed-help-requests" /></th>
		<jstl:forEach var="prices" items="${maximumBudgetOfProposedHelpRequests}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
		<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-budget-of-accepted-help-requests" /></th>
		<jstl:forEach var="prices" items="${averageBudgetOfAcceptedHelpRequests}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-budget-of-accepted-help-requests" /></th>
		<jstl:forEach var="prices" items="${deviationBudgetOfAcceptedHelpRequests}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-budget-of-accepted-help-requests" /></th>
		<jstl:forEach var="prices" items="${minimumBudgetOfAcceptedHelpRequests}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-budget-of-accepted-help-requests" /></th>
		<jstl:forEach var="prices" items="${maximumBudgetOfAcceptedHelpRequests}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
			<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-budget-of-denied-help-requests" /></th>
		<jstl:forEach var="prices" items="${averageBudgetOfDeniedHelpRequests}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-budget-of-denied-help-requests" /></th>
		<jstl:forEach var="prices" items="${deviationBudgetOfDeniedHelpRequests}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-budget-of-denied-help-requests" /></th>
		<jstl:forEach var="prices" items="${minimumBudgetOfDeniedHelpRequests}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-budget-of-denied-help-requests" /></th>
		<jstl:forEach var="prices" items="${maximumBudgetOfDeniedHelpRequests}">
			<td><acme:print value="${prices}" /></td>
		</jstl:forEach>
	</tr>
</table>

<h2>
	<acme:message
		code="administrator.dashboard.form.title.total-number-of-tutorials" />
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<h2>
	<acme:message
		code="administrator.dashboard.form.title.total-number-of-help-requests" />
</h2>
<div>
	<canvas id="canvas2"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"THEORY TUTORIALS","LAB TUTORIALS"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${totalNumberOfTheoryTutorials}"/>,
						<jstl:out value="${totalNumberOfLabTutorials}"/>
					]
				}
			]
		};
		var data1 = {
				labels : [
						"PROPOSED HELP REQUESTS","ACCEPTED HELP REQUESTS","DENIED HELP REQUESTS"
				],
				datasets : [
					{
						data : [
							<jstl:out value="${totalNumberOfProposedHelpRequests}"/>,
							<jstl:out value="${totalNumberOfAcceptedHelpRequests}"/>,
							<jstl:out value="${totalNumberOfDeniedHelpRequests}"/>
						]
					}
				]
			};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 10.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
		var options1 = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 10.0
							}
						}
					]
				},
				legend : {
					display : false
				}
			};
	
		var canvas,canvas2, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
		canvas2 = document.getElementById("canvas2");
		context = canvas2.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data1,
			options : options1
		});
	});
</script>



