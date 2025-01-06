import { configureStore } from "@reduxjs/toolkit";
import settlementSlice from "./SettlementSlice";
import newsSlice from "./NewsSlice";
import pageSlice from "./PageSlice";
import visibilitySlice from "./Visibility";

const store = configureStore({    
    reducer: {
        settlement: settlementSlice,
        news: newsSlice,
        page: pageSlice,
        visibility: visibilitySlice
    }
})

export default store