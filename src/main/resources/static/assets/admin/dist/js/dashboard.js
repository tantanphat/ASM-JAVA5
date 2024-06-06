//Hoá đơn thấp nhất và cao nhất
function dashboardDoanhThuMinMax(callback) {
    var data = [];
    var minDT = [];
    var maxDT = [];
    var formData = {
        minDT: minDT,
        maxDT: maxDT
    };
    function doanhThuDash(month) {
        $.ajax({
            url: '/api/thong-ke/doanh-thu/' + month,
            type: 'GET',
            dataType: 'json',
            success: function (response) {
                response.forEach(function (item) {
                    minDT.push(item[3] * 1000);
                    maxDT.push(item[4] * 1000);
                    data.push(item[2] * 1000);
                });
                if (month === 12) {
                    callback(formData);
                } else {
                    doanhThuDash(month + 1);
                }
            }
        });
    }
    doanhThuDash(1);
}

//Doanh thu
function dashboardDoanhThu(callback) {
    var data = [];

    function doanhThuDash(month) {
        $.ajax({
            url: '/api/thong-ke/doanh-thu/' + month,
            type: 'GET',
            dataType: 'json',
            success: function (response) {
                response.forEach(function (item) {
                    data.push(item[2] * 1000);
                });
                if (month === 12) {
                    callback(data);
                } else {
                    doanhThuDash(month + 1);
                }
            }
        });
    }
    doanhThuDash(1);
}
dashboardDoanhThuMinMax(updateChart);
dashboardDoanhThu(updateChartDoanhThu)

function updateChart(formData) {
    const ctx = document.getElementById("myChart").getContext("2d");
    const myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            ],
            datasets: [{
                label: 'Hóa đơn thấp nhất',
                data: formData.minDT,
                lineTension: 0,
                backgroundColor: 'transparent',
                borderColor: 'red',
                borderWidth: 4,
                pointBackgroundColor: 'red',
            },
                {
                    label: 'Hóa đơn cao nhất',
                    data: formData.maxDT,
                    lineTension: 0,
                    backgroundColor: 'transparent',
                    borderColor: 'blue',
                    borderWidth: 4,
                    pointBackgroundColor: 'blue',
                }
            ]
        },
        options: {
            animation: true,
            plugins: {
                legend: {
                    display: true,
                },
                tooltip: {
                    boxPadding: 3,
                },
            },
        },
    });
}

function updateChartDoanhThu(data) {
    const ctx = document.getElementById("dashDoanhThu").getContext("2d");
    const myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            ],
            datasets: [{
                label: 'Doanh thu',
                data: data,
                lineTension: 0,
                backgroundColor: 'red',//Các dấu chấm
                borderWidth: 1,
                borderColor: 'black',
                pointStyle:'circle',
                pointRadius: 5,
                pointHoverBackgroundColor: 'pink',

            }
            ]
        },
        options: {
            plugins: {
                legend: {
                    display: true,
                },
                tooltip: {
                    boxPadding: 3,
                },
            },
        },
    });
}
