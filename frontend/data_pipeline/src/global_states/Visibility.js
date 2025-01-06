import { createSlice } from '@reduxjs/toolkit';

const visibilitySlice = createSlice({
    name: "visibility",
    initialState: {
        paginationVisibility: false,
    },
    reducers: {
        changePaginationVisibility: (state) => {state.paginationVisibility = true},
    }
})

export default visibilitySlice.reducer
export const {changePaginationVisibility} = visibilitySlice.actions