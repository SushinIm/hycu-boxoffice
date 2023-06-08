const digit2 = (target) => {
  return target < 10 ? '0' + target : target;
}

const setMovieChart = (boxOffice) => {
  const chartArea = document.querySelector("div#chart");
  chartArea.replaceChildren();
}

const setMovieNameList = (movieNameList) => {
  const navArea = document.querySelector("div#nav");
  navArea.replaceChildren();
  movieNameList.map(movieName => {
    let tagP = document.createElement("p");
    let tagA = document.createElement("a");
    tagA.textContent = movieName;
    tagP.appendChild(tagA);
    navArea.appendChild(tagP);
  });
}

const setBoxOfficeChart = (movieData) => {
  document.querySelector("div#chart").replaceChildren();
  google.charts.load('current', {'packages':['corechart']});
  google.charts.setOnLoadCallback(() => {
    let startDate = document.querySelector("#startDate").value;
    let endDate = document.querySelector("#endDate").value;
    let dataTable = [['영화 제목', '누적 매출액', '누적 관객수', '상영 횟수', '상영관 수']];
    movieData.forEach(data => dataTable.push([data.movieName,
      data.salesAccumulate,
      data.audienceAccumulate,
      data.showCount,
      data.screenCount]));

    let chartData = google.visualization.arrayToDataTable(dataTable);
    let options = {
      title: `${startDate} ~ ${endDate} 박스오피스 조회 정보`,
      hAxis: {title: '누적 매출액'},
      vAxis: {title: '누적 관객수'},
      bubble: {textStyle: {fontSize: 11}}
    };
    let chart = new google.visualization.BubbleChart(document.getElementById('chart'));
    chart.draw(chartData, options);
  });
}