
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
      minHeight: "100vh",
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
