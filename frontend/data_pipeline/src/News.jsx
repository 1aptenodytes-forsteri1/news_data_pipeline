import { Box, Typography, PaginationItem } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import Article from "./Article";
import { changePage } from "./global_states/PageSlice";

function News() {
  const news = useSelector((state) => state.news.articles);
  const page = useSelector((state) => state.page);
  const paginationVisibility = useSelector(state => state.visibility.paginationVisibility);
  const dispatch = useDispatch();

  const articlesPerPage = 15;

  const indexOfLastArticle = page * articlesPerPage;
  const indexOfFirstArticle = indexOfLastArticle - articlesPerPage;
  const currentArticles = news.slice(indexOfFirstArticle, indexOfLastArticle);

  const totalPages = Math.ceil(news.length / articlesPerPage);
  
  return (
      <Box sx={{ margin: 5 }}>
      <Box
        sx={{
          display: "flex",
          flexWrap: "wrap",
          justifyContent: "center",
          gap: 2,
          paddingX: 30,
          paddingY: 5,
        }}
      >
        {currentArticles.map((article, index) => (
          <Article news={article} key={index} />
        ))}
      </Box>

      <Box
        sx={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          marginTop: 3,
        }}
      >
      </Box>

      {paginationVisibility && (
        <Box sx={{ display: "flex", justifyContent: "center", marginTop: 2 }}>
          <PaginationItem
            type="previous"
            onClick={() => dispatch(changePage(Math.max(page - 1, 1)))}
            sx={{ marginX: 2, cursor: page > 1 ? "pointer" : "not-allowed" }}
          />
          <Typography>{page}</Typography>
          <PaginationItem
            type="next"
            onClick={() => dispatch(changePage(Math.min(page + 1, totalPages)))}
            sx={{ marginX: 2, cursor: page < totalPages ? "pointer" : "not-allowed" }}
          />
        </Box>
      )}
    </Box>
    
    

  );
}

export default News;
