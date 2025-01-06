
import { Card, CardMedia, CardContent, Typography} from "@mui/material";
import { Textfit } from "react-textfit";


function Article(news, key){

  const parceDate = (date)=>{
    const startTime = new Date(date);
    const now = new Date();
    const diffInMilliseconds = now - startTime;

    const millisecondsInSecond = 1000;
    const millisecondsInMinute = millisecondsInSecond * 60;
    const millisecondsInHour = millisecondsInMinute * 60;
    const millisecondsInDay = millisecondsInHour * 24;

    const days = Math.floor(diffInMilliseconds / millisecondsInDay);
    const hours = Math.floor((diffInMilliseconds % millisecondsInDay) / millisecondsInHour);
    const minutes = Math.floor((diffInMilliseconds % millisecondsInHour) / millisecondsInMinute);

    if(days){
      return days + " days ago"
    }
    if(hours){
      return hours + " hours ago"
    }
    if(minutes){
      return minutes + " minutes ago"
    }
    return date
  }

    return(<Card
      key={key}
      sx={{
        width: 300,
        display: "flex",
        flexDirection: "column",
        justifyContent: "space-between",
        overflow: "hidden",
        borderRadius: 4,
        boxShadow: 3,
      }}
    >
      <CardMedia
        component="img"
        height="150"
        image={news.news.imageUrl || "news.png"} 
        alt="news images"
        onError={(e) => {
          e.target.onerror = null;
          e.target.src = "news.png"; 
        }}
      />
      <CardContent>
        <Typography
          variant="body2"
          color="textSecondary"
          sx={{ marginBottom: 1 }}
        >
          {parceDate(news.news.time)}
        </Typography>

        <Textfit mode="multi" max={24} style={{ height: "60px" }}>
          <Typography

            component="a"
            fontSize = {14}
            href={news.news.url}
            target="_blank"
            sx={{
              textDecoration: "none",
              color: "inherit",
              display: "-webkit-box",
              WebkitLineClamp: 3,
              WebkitBoxOrient: "vertical",
              overflow: "hidden",
            }}
          >
            {news.news.title}
          </Typography>
        </Textfit>
      </CardContent>
    </Card>)
}

export default Article
