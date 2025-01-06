import { useState } from "react";
import { TextField, Button, Box } from "@mui/material";
import { useSelector, useDispatch } from "react-redux";
import { changeSettlement } from "./global_states/SettlementSlice";
import axios from 'axios'
import { addNews, changeNews, sortNews } from "./global_states/NewsSlice";
import debounce from 'lodash/debounce';
import { changePage } from "./global_states/PageSlice";
import {changePaginationVisibility} from "./global_states/Visibility"


function SettlementInput() {

  const [inputValue, setInputValue] = useState("");
  const [sortButtonIsDisabled, setsortButtonIsDisabled] = useState(true);
  const settlement = useSelector(state=>state.settlement)
  const news = useSelector((state) => state.news.articles);
  const dispatch = useDispatch();
  let eventSource
  
  const handleChange = (event) => {
    setInputValue(event.target.value);
  };

  const handleSettlementButtonClick = (name) => {
    axios.get("http://localhost:80/settlement", {
      params: {
        name: name
      }
    }).then(response => {
      dispatch(changeSettlement(response.data.toponymName))
    })
  }

  

  const handleRequestNews = () => {
    
    setsortButtonIsDisabled(false)
    dispatch(changePage(1))
    dispatch(changePaginationVisibility())
    if(!news.lengh) dispatch(changeNews())
    
    if (eventSource) {  
      eventSource.close();  
    }

    eventSource = new EventSource("http://localhost:80/news?name=" + settlement)


    const debouncedDispatch = debounce((news) => {
        dispatch(addNews(news));
      }, 10);

    eventSource.onmessage = (event) => {
        const newArticle = JSON.parse(event.data);

        debouncedDispatch(newArticle);
    };


    eventSource.onerror = () => {
      eventSource.close()
    }
  }

  const handleNewsSort = ()=>{
    dispatch(sortNews())
  }

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        gap: 2,
        p: 4,
        border: "1px solid #ddd",
        borderRadius: 2,
        boxShadow: 2,
        maxWidth: 500,
        backgroundColor: "white",
      }}
    >
      <TextField
        label="Choose your location"
        variant="outlined"
        value={inputValue}
        onChange={handleChange}
        fullWidth
      />

      <Box sx={{ display: "flex", gap: 2 }}>
        <Button
          variant="contained"
          color="primary"
          onClick={() => handleSettlementButtonClick(inputValue)}
        >
          Find settlement
        </Button>
        <Button
          disabled={!settlement}
          variant="outlined"
          color="secondary"
          onClick={handleRequestNews}
        >
          Find news
        </Button>
        <Button
          disabled={sortButtonIsDisabled}
          variant="outlined"
          color="secondary"
          onClick={handleNewsSort}
        >
        Sort news
        </Button>
      </Box>
    </Box>
  );
};


export default SettlementInput;
