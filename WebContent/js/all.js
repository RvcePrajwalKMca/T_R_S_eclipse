const badge = document.querySelector("#hover_badge");
if (parseInt(badge.innerText.replace("(","").replace(")","").replace(" ","")) == 0) {
	badge.style.display = "none";
}