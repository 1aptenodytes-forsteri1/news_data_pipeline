// import { useSelector, useDispatch } from 'react-redux'
// import { changeSettlement } from './global_states/SettlementSlice'
// import { addNews, changeNews } from './global_states/NewsSlice'
// import './App.css'
// import { useState } from 'react'
// import axios from 'axios'

// function App() {
//   const [inputText, setInputText] = useState("")
//   const settlement = useSelector((state) => state.settlement)
//   const news = useSelector((state) => state.news)
//   const dispatch = useDispatch();
//   let eventSource 

//   const handleRequestNews = () => {
//     console.log("click")
//     if (eventSource) {  
//       eventSource.close();  
//     }
    
//     eventSource = new EventSource("http://localhost:80/news?name=" + settlement)
    
//     eventSource.onmessage = (event) => {
//       console.log(event.data)
//       dispatch(addNews(JSON.parse(event.data)))
//     }


//     eventSource.onerror = () => {
//       console.error("Error with EventSource")
//       eventSource.close();
//     }
//   }

//   const handleText = (e) => {
//     setInputText(e.target.value)
//   }

//   const getSettlementData = (name) => {
//     axios.get("http://localhost:80/settlement", {
//       params: {
//         name: name
//       }
//     }).then(response => {
//       dispatch(changeSettlement(response.data.toponymName))
//     })
//   }

//   return (
//     <>
//       <div>
//         <p>{settlement}</p>
//         <input 
//           type="text" 
//           placeholder="Enter the city"
//           value={inputText}
//           onChange={handleText}
//         />
//         <button onClick={() => { if (inputText) getSettlementData(inputText) }}>
//           Accept
//         </button>
//         <button onClick={handleRequestNews}>
//           Get News
//         </button>
//       </div>
//       <ul>
//         {news.map((element, index) => (
//           <li key={index}>
//             <a href={element.url} target='_blank' rel='noopener noreferrer'>
//               {element.title}
//             </a>
//           </li>
//         ))}
//       </ul>
//     </>
//   )
// }

// export default App;







// import React from "react";
// import { Card, CardMedia, CardContent, Typography, Link, Box } from "@mui/material";

// // Пример данных новостей
// const newsData = [
//   {
//     id: 1,
//     title: "Заголовок первой новости",
//     image: "https://via.placeholder.com/300x200",
//     date: "2024-06-09",
//     link: "https://example.com/article1",
//   },
//   {
//     id: 2,
//     title: "Заголовок второй новости",
//     image: "https://via.placeholder.com/300x200",
//     date: "2024-06-10",
//     link: "https://example.com/article2",
//   },
//   {
//     id: 3,
//     title: "Заголовок третьей новости",
//     image: "https://via.placeholder.com/300x200",
//     date: "2024-06-11",
//     link: "https://example.com/article3",
//   },
//   {
//     id: 4,
//     title: "Заголовок первой новости",
//     image: "https://via.placeholder.com/300x200",
//     date: "2024-06-09",
//     link: "https://example.com/article1",
//   },
//   {
//     id: 5,
//     title: "Заголовок второй новости",
//     image: "https://via.placeholder.com/300x200",
//     date: "2024-06-10",
//     link: "https://example.com/article2",
//   },
//   {
//     id: 6,
//     title: "Заголовок третьей новости",
//     image: "https://via.placeholder.com/300x200",
//     date: "2024-06-11",
//     link: "https://example.com/article3",
//   },
//   {
//     id: 7,
//     title: "Заголовок первой новости",
//     image: "https://via.placeholder.com/300x200",
//     date: "2024-06-09",
//     link: "https://example.com/article1",
//   },
//   {
//     id: 8,
//     title: "Заголовок второй новости",
//     image: "https://via.placeholder.com/300x200",
//     date: "2024-06-10",
//     link: "https://example.com/article2",
//   },
//   {
//     id: 9,
//     title: "Заголовок третьей новости",
//     image: "https://via.placeholder.com/300x200",
//     date: "2024-06-11",
//     link: "https://example.com/article3",
//   },
// ];

// function NewsList() {
//   return (
//     <Box
//       sx={{
//         display: "flex",
//         flexWrap: "wrap",
//         justifyContent: "center",
//         gap: 2, // Отступы между элементами
//         paddingX: 30, // Большие отступы по краям экрана
//         paddingY: 5, // Отступы по вертикали
//       }}
//     >
//       {newsData.map((news) => (
//         <Card
//           key={news.id}
//           sx={{
//             width: 250,
//             height: 300,
//             display: "flex",
//             flexDirection: "column",
//             justifyContent: "space-between",
//             boxShadow: 3,
//             borderRadius: 2,
//             transition: "transform 0.3s",
//             "&:hover": { transform: "scale(1.05)" },
//           }}
//         >
//           <CardMedia
//             component="img"
//             image={news.image}
//             alt={news.title}
//             sx={{
//               height: 140,
//             }}
//           />
//           <CardContent sx={{ flexGrow: 1 }}>
//             <Typography
//               variant="body2"
//               color="text.secondary"
//               gutterBottom
//               align="center"
//             >
//               {news.date}
//             </Typography>
//             <Link
//               href={news.link}
//               underline="hover"
//               color="primary"
//               sx={{ display: "block", textAlign: "center" }}
//             >
//               <Typography variant="h6" component="div">
//                 {news.title}
//               </Typography>
//             </Link>
//           </CardContent>
//         </Card>
//       ))}
//     </Box>
//   );
// }

// export default NewsList;




// import React, { useState } from "react";
// import { TextField, Button, Box } from "@mui/material";

// const InputWithButtons = () => {
//   const [inputValue, setInputValue] = useState("");

//   const handleChange = (event) => {
//     setInputValue(event.target.value);
//   };

//   const handleButtonOneClick = () => {
//     alert(`Первая кнопка нажата. Текст: ${inputValue}`);
//   };

//   const handleButtonTwoClick = () => {
//     alert(`Вторая кнопка нажата. Поле очищено.`);
//     setInputValue("");
//   };

//   return (
//     <Box
//       sx={{
//         //height: "100vh", // Занимаем всю высоту экрана
//         width: "100vh",
//         display: "flex", // Flexbox
//         justifyContent: "center", // Центрирование по горизонтали
//         alignItems: "center", // Центрирование по вертикали
//         backgroundColor: "#f4f4f4", // Светлый фон
//       }}
//     >
//       <Box
//         sx={{
//           display: "flex",
//           flexDirection: "column", // Элементы располагаются в столбик
//           alignItems: "center", // Центрирование по горизонтали
//           gap: 2, // Отступы между дочерними элементами
//           p: 4, // Паддинги вокруг блока
//           border: "1px solid #ddd", // Серая граница
//           borderRadius: 2, // Скругление углов
//           boxShadow: 3, // Тень вокруг компонента
//           backgroundColor: "white", // Фон компонента
//           maxWidth: 400, // Максимальная ширина
//           width: "100%", // Адаптивная ширина
//         }}
//       >
//         <TextField
//           label="Введите текст"
//           variant="outlined"
//           value={inputValue}
//           onChange={handleChange}
//           fullWidth
//         />
//         <Box sx={{ display: "flex", gap: 2 }}>
//           <Button
//             variant="contained"
//             color="primary"
//             onClick={handleButtonOneClick}
//           >
//             Первая кнопка
//           </Button>
//           <Button
//             variant="outlined"
//             color="secondary"
//             onClick={handleButtonTwoClick}
//           >
//             Вторая кнопка
//           </Button>
//         </Box>
//       </Box>
//     </Box>
//   );
// };

// export default InputWithButtons;










import { Box, Typography } from "@mui/material"
import News from "./News"
import SettlementInput from "./SettlementInput"
import { useSelector } from "react-redux"
function App(){
  const settlement = useSelector(state=>state.settlement)
  return(
    <Box
    sx={{
      display: "flex",
      flexDirection: "column",
      alignItems: "center",
      justifyContent: "center",
      minHeight: "100vh", // Полная высота экрана
      padding: 0,
    }}
    >
      <Typography
      variant="h3"
      sx={{
        fontWeight:"bold",
        textAlign:"center",
      }}
      >{settlement}</Typography>
      <SettlementInput/>
      <News/>
    </Box>
  )
}


export default App









// import React, { useState } from "react";
// import { Container, Grid, Card, CardMedia, CardContent, Typography, Pagination } from "@mui/material";

// const newsData = [
//   // Здесь должен быть массив объектов новостей
//   { id: 1, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 2, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 3, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 4, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 5, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 6, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 7, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 8, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 9, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 10, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 11, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 12, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 13, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 14, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 15, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 16, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 17, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 18, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 19, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 20, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 21, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 22, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 23, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 24, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 25, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 26, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 27, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 28, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 29, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 30, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 31, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 32, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 33, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 34,title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 35, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 36, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 37, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 38, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 39, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 40, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 41, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 42, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 43, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 44, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 45, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 46, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 47, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 48, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   { id: 49, title: "News 1", date: "2024-06-01", imageUrl: "image1.png" },
//   { id: 50, title: "News 2", date: "2024-06-02", imageUrl: "image2.png" },
//   // Добавьте больше статей для примера
// ];

// const App = () => {
//   const [currentPage, setCurrentPage] = useState(1); // Текущая страница
//   const articlesPerPage = 15; // Количество статей на странице

//   // Определяем индекс первой и последней статьи на текущей странице
//   const indexOfLastArticle = currentPage * articlesPerPage;
//   const indexOfFirstArticle = indexOfLastArticle - articlesPerPage;
//   const currentArticles = newsData.slice(indexOfFirstArticle, indexOfLastArticle);

//   const handlePageChange = (event, value) => {
//     setCurrentPage(value);
//   };

//   return (
//     <Container>
//       <Grid container spacing={3}>
//         {currentArticles.map((news) => (
//           <Grid item xs={12} sm={6} md={4} key={news.id}>
//             <Card>
//               <CardMedia
//                 component="img"
//                 height="150"
//                 image={news.imageUrl || "news.png"}
//                 alt="news image"
//               />
//               <CardContent>
//                 <Typography variant="h6" noWrap>
//                   {news.title}
//                 </Typography>
//                 <Typography variant="body2" color="text.secondary">
//                   {news.date}
//                 </Typography>
//               </CardContent>
//             </Card>
//           </Grid>
//         ))}
//       </Grid>

//       {/* Пагинация */}
//       <Pagination
//         count={Math.ceil(newsData.length / articlesPerPage)} // Количество страниц
//         page={currentPage}
//         onChange={handlePageChange}
//         color="primary"
//         style={{ display: "flex", justifyContent: "center", marginTop: "20px" }}
//       />
//     </Container>
//   );
// };

// export default App;
