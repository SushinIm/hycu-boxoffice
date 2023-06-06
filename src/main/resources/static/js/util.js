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

const setMovieNameList = (data) => {
  const navArea = d3.select("body > div#wrap > div#nav");
  const movieNameList = Array.from(new Set(data.map(boxOffice => boxOffice.movieName)));
  navArea.selectAll("p").data(movieNameList).enter().append("p").append("a").text((movieName) => movieName);
}

const setBoxOfficeChart = (data) => {
  const chartArea = d3.select("body > div#wrap > div#chart");
}