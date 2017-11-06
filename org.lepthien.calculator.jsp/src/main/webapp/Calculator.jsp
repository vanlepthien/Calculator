<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="1.2">
	<jsp:directive.page
		import="org.lepthien.calculator.jsp.CalculatorController" />
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
		import="java.math.BigInteger " />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	CalculatorController c = new CalculatorController();
	int base = c.getBase();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Calculator</title>

<script type="text/javascript">
	<![CDATA[
	var value = 0;
	var base = 10;
	var ns = "http://www.w3.org/1999/xhtml";

	//setBase(10);

	function numericButtonClick(number) {
		if (value == 0) {
			value = number;
		} else if (value >= 0) {
			value = value * base + number;
		} else {
			value = value * base - number;
		}
		redisplayValue();
		return value;
	}

	function redisplayValue() {
		var dVal = getDisplayValue();
		document.getElementById("valueLabelContainer").innerHTML = dVal;
	}

	function getDisplayValue() {
		if (value == 0) {
			return "0"
		}
		var retVal = "";
		var val = Math.abs(value);
		var sign = "";
		if (value < 0) {
			sign = "-";
		}
		while (val > 0) {
			var next = Math.floor(val / base);
			var rem = val % base;
			var digit = getDigit(rem);
			retVal = digit + retVal;
			val = next;
		}
		return sign + retVal;
	}

	// TODO: Fix Calculator input

	var digits = "0123456789abcdefghijklmnopqrstuvwxyz@ABCDEFGHIJKLMNOPQRSTUVWXYZ$";

	function getDigit(number) {
		if (base > 64) {
			var baseLimit = base - 1;
			var baseSize = baseLimit.toString(16).length;
			var sDigit = number.toString(16);
			return "'" + ("000" + sDigit).slice(-baseSize) + "'";
		} else {
			return digits.substr(number, 1);
		}
	}

	function post(field, value) {
		var form = document.createElement(form)
		form.setAttribute("method", "POST")
		form.setAttribute("field", field)
		if (!(value === undefined)) {
			form.setAttribute("value", value)
		}

	}

	function clear() {
		value = 0;
		setRegister(0);
		redisplayValue();
	}

	function clearEntry() {
		value = 0;
		redisplayValue();
	}

	function setBase(number) {
		base = number;
		var baseField = document.getElementById("baseField")
		baseField.value = base;
		redisplayValue();
		generateNumericPad();
	}

	function setBaseFromField() {
		var stringBase = document.getElementById("baseField").value;
		var parsedBase = parseInt(stringBase);
		setBase(parsedBase);
	}

	function removeChildren(node) {
		var children = node.childNodes;
		var count = node.childNodes.length;
		for (var ix = 0; ix < count; ix++) {
			node.removeChild(children[0]);
		}
	}

	]]>
</script>

</head>
<body>

	<h1>Calculator</h1>
	<form method="post">
		<table align="center">
			<tr>
				<td id="valueLabelContainer"><%=c.formatValue()%></td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td>
								<button id="clearButton" name="clearButton" onclick="clear()">C</button>
							</td>
							<td>
								<button id="clearEntryButton" name="clearEntryButton"
									onclick="clearEntryClick()">CE</button>
							</td>
							<td>
								<button id="octButton" name="octButton" onclick="setBase(8)">oct</button>
							</td>
							<td>
								<button id="decButton" name="decButton" onclick="setBase(10)">dec</button>
							</td>
							<td>
								<button id="hexButton" name="hexButton" onclick="setBase(16)">hex</button>
							</td>
						</tr>
					</table>
					<table>
						<tr>
							<td span="2"><input type="text" name="baseField"
								id="baseField" pattern="\\d+" value="10" /></td>
							<td>
								<button id="baseChangeButton" name="baseChangeButton"
									onclick="setBaseFromField()">Change Base</button>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table id="numericPadTable">
						<%
							double root = Math.floor(Math.sqrt(base));
							int cols = (int) root;
							int rows = (int) Math.ceil(base / cols);
							int rem = (rows * cols) - base;


							for (int row = 0; row < rows; row++) {
								%>
								<tr>
								<%
								for (int col = 0; col < cols; col++) {
									int ix = ((rows - row) * cols) + (col - cols) - rem;
									String digit;
									if (ix >= 0) {
										digit = c.getBaseDigit(ix);
										%>
										<td>
										<input type="submit" name="b_<%= digit %>" ><%= digit %> </button>
										<%
									}
								}
							}
						%>

					</table>
				</td>
				<td>
					<table>
						<tr>
							<td><button id="changeSignButton" name="changeSignButton">&#xb1;</button></td>
						</tr>
						<tr>
							<td><button id="addButton" name="addButton">+</button></td>
						</tr>
						<tr>
							<td><button id="subtractButton" name="subtractButton">-</button></td>
						</tr>
						<tr>
							<td><button id="multiplyButton" name="multiplyButton">*</button></td>
						</tr>
						<tr>
							<td><button id="divideButton" name="divideButton">/</button></td>
						</tr>
						<tr>
							<td><button id="equalsButton" name="equalsButton">=</button></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="color: red;" id="errorLabelContainer"></td>
			</tr>
		</table>
	</form>
</body>
	</html>
</jsp:root>