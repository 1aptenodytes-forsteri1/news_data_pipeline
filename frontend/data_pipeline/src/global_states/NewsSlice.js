import { createSlice } from "@reduxjs/toolkit";

const newsSlice = createSlice({
    name: "news",
    initialState: {
        articles:[]
    },
    reducers: {
        changeNews: (state) => {state.articles = []},
        addNews: (state, action) => {state.articles.push(action.payload);},
        sortNews: (state) => {state.articles = [...state.articles].sort((a, b) => new Date(b.time) - new Date(a.time))}
    }
}
)

export default newsSlice.reducer
export const {changeNews, addNews, sortNews} = newsSlice.actions
