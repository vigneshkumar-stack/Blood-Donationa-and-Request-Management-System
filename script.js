let donors=JSON.parse(localStorage.getItem("donors"))||[];
let requests=JSON.parse(localStorage.getItem("requests"))||[];

function showSection(id){

let sections=document.querySelectorAll(".section");
sections.forEach(s=>s.style.display="none");

document.getElementById(id).style.display="block";

}

function addDonor(){

let donor={
name:dname.value,
blood:dblood.value,
phone:dphone.value,
city:dcity.value
};

donors.push(donor);

localStorage.setItem("donors",JSON.stringify(donors));

alert("Donor Registered Successfully!");

loadDashboard();

}

function addRequest(){

let req={
name:pname.value,
blood:pblood.value,
units:units.value,
phone:pphone.value,
hospital:hospital.value
};

requests.push(req);

localStorage.setItem("requests",JSON.stringify(requests));

alert("Blood Request Submitted!");

loadDashboard();

}

function searchDonor(){

let blood=document.getElementById("searchBlood").value;

let table="<tr><th>Name</th><th>Blood</th><th>Phone</th><th>City</th></tr>";

donors.forEach(d=>{

if(d.blood===blood){

table+=`<tr>
<td>${d.name}</td>
<td>${d.blood}</td>
<td>${d.phone}</td>
<td>${d.city}</td>
</tr>`;

}

});

document.getElementById("searchTable").innerHTML=table;

}

function loadDashboard(){

let dtable="<tr><th>Name</th><th>Blood</th><th>Phone</th><th>City</th></tr>";

donors.forEach(d=>{
dtable+=`<tr>
<td>${d.name}</td>
<td>${d.blood}</td>
<td>${d.phone}</td>
<td>${d.city}</td>
</tr>`;
});

document.getElementById("donorTable").innerHTML=dtable;

let rtable="<tr><th>Patient</th><th>Blood</th><th>Units</th><th>Phone</th><th>Hospital</th></tr>";

requests.forEach(r=>{
rtable+=`<tr>
<td>${r.name}</td>
<td>${r.blood}</td>
<td>${r.units}</td>
<td>${r.phone}</td>
<td>${r.hospital}</td>
</tr>`;
});

document.getElementById("requestTable").innerHTML=rtable;

}

showSection("donor");
loadDashboard();