import { createSlice } from "@reduxjs/toolkit";

const pageSlice = createSlice({
    name:"page",
    initialState: 1,
    reducers:{
        changePage: (state, action ) => action.payload
    }
})

export default pageSlice.reducer
export const {changePage} = pageSlice.actions