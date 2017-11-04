<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="1.2">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
		import="java.math.BigInteger" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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

	function generateNumericPad() {

		var root = Math.floor(Math.sqrt(base));
		var cols = root;
		var rows = Math.ceil(base / cols);
		var rem = (rows * cols) - base;

		var table = document.getElementById("numericPadTable");
		removeChildren(table);

		for (var row = 0; row < rows; row++) {
			var tr = document.createElement("tr");
			table.appendChild(tr);
			for (var col = 0; col < cols; col++) {
				var ix = ((rows - row) * cols) + (col - cols) - rem;
				var digit;
				if (ix >= 0) {
					digit = getDigit(ix);

					var td = document.createElement("td");
					tr.appendChild(td);
					var button = "<button onclick=\"numericButtonClick(" + ix
							+ ")\">" + digit + "</button>";
					td.innerHTML = button;
				}
			}
		}
	}
	]]>
</script>

</head>
<body>

	<h1>Calculator</h1>
	<table align="center">
		<tr>
			<td id="valueLabelContainer">0</td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td>
							<button id="clearButton" onclick="clear()">C</button>
						</td>
						<td>
							<button id="clearEntryButton" onclick="clearEntryClick()">CE</button>
						</td>
						<td>
							<button id="octButton" onclick="setBase(8)">oct</button>
						</td>
						<td>
							<button id="decButton" onclick="setBase(10)">dec</button>
						</td>
						<td>
							<button id="hexButton" onclick="setBase(16)">hex</button>
						</td>
					</tr>
				</table> 
				<table>
					<tr>
						<td span="2"><input type="text" name="baseField"
							id="baseField" pattern="\\d+" value="10" /></td>
						<td>
							<button id="baseChangeButton" onclick="setBaseFromField()">Change
								Base</button>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table id="numericPadTable">
					<tr>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td><button id="changeSignButton">&#xb1;</button></td>
					</tr>
					<tr>
						<td><button id="addButton">+</button></td>
					</tr>
					<tr>
						<td><button id="subtractButton">-</button></td>
					</tr>
					<tr>
						<td><button id="multiplyButton">*</button></td>
					</tr>
					<tr>
						<td><button id="divideButton">/</button></td>
					</tr>
					<tr>
						<td><button id="equalsButton">=</button></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="color: red;" id="errorLabelContainer"></td>
		</tr>
	</table>
</body>
	</html>
</jsp:root>