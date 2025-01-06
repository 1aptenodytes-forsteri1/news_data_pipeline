import { createSlice } from '@reduxjs/toolkit';

const settlementSlice = createSlice({
    name: "settlement",
    initialState: null,
    reducers: {
        changeSettlement: (state, action) => action.payload
    }
})

export default settlementSlice.reducer
export const {changeSettlement} = settlementSlice.actions