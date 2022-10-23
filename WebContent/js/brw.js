const para = document.querySelectorAll(".details > p");

function trunc(s, maxLength) {
	if (s.length > maxLength) {
		return s.substring(0, maxLength) + "...";
	}
	return s;
}

para.forEach((p) => {
	p.innerHTML = trunc(p.innerHTML.trim(), 116);
});


const data_badge = document.querySelectorAll(".cart > .data-badge");

const plus = document.querySelectorAll(".cart > .plus");
plus.forEach((p, i) => {
	p.addEventListener("click", () => {
		data_badge[i].style.display = "block";
		data_badge[i].innerHTML = parseInt(data_badge[i].innerHTML) + 1;
		if (parseInt(data_badge[i].innerHTML) >= 50) {
			data_badge[i].innerHTML = "50";
		}
	});
});

const minus = document.querySelectorAll(".cart > .minus");
minus.forEach((p, i) => {
	p.addEventListener("click", () => {
		data_badge[i].style.display = "block";
		data_badge[i].innerHTML = parseInt(data_badge[i].innerHTML) - 1;
		if (parseInt(data_badge[i].innerHTML) <= 0) {
			data_badge[i].style.display = "none";
			data_badge[i].innerHTML = "0";
		}
	});
});
