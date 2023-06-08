const digit2 = (target) => {
  return target < 10 ? '0' + target : target;
}

const setMovieChart = (boxOffice) => {
  const chartArea = d3.select("body > div#wrap > div#chart");
  chartArea.select("*").remove();
  const movieData = boxOffice.filter(movie => boxOffice.movieName === movie.movieName);
  console.log(movieData);
  //TODO 순위
  //TODO 매출
  //TODO 관객
  //TODO 상영
  //TODO 날짜별로 꺾은선 그래프?
}

const setMovieNameList = (movieNameList) => {
  const navArea = d3.select("body > div#wrap > div#nav");
  navArea.selectAll("p").data(movieNameList).enter().append("p").append("a").text((movieName) => movieName);
}

const setBoxOfficeChart = (data) => {
  const chartArea = d3.select("body > div#wrap > div#chart");
  chartArea.selectChildren().remove();
  const chartDataMap = {
    "audienceAccumulateList" : data.map(boxOffice => boxOffice.audienceAccumulate),
    "salesAccumulateList" : data.map(boxOffice => boxOffice.salesAccumulate),
    "screenCountList" : data.map(boxOffice => boxOffice.screenCount),
    "showCountList" : data.map(boxOffice => boxOffice.showCount),
  }

  Object.keys(chartDataMap).forEach(key => {
    const dataList = chartDataMap[key];
    chartArea.append("svg")
    .selectAll("rect")
    .data(dataList)
    .enter()
    .append("rect")
    .attr("class", "bar")
    .attr("height", (d, i) => {
      return d;
    })
    .attr("width", 15)
    .attr("x", (d, i) => {
      return i * 25;
    })
    .attr("y", (d, i) => {
      return 0;
    })
  })
}