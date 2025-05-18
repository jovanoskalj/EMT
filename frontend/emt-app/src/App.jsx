import React from 'react';
import CountriesPage from "./ui/pages/CountriesPage/CountriesPage.jsx";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Layout from "./ui/components/layout/Layout/Layout.jsx";
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import CountryDetails from "./ui/components/countries/CountryDetails/CountryDetails.jsx";
import HostDetails from "./ui/components/hosts/HostDetails/HostDetails.jsx";

import HostsPage from "./ui/pages/HostsPage/HostsPage.jsx";
import AccommodationsPage from "./ui/pages/AccommodationsPage/AccommodationsPage.jsx";
import AccommodationDetails from "./ui/components/accommodations/AccommodationDetails/AccommodationDetails.jsx";

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<HomePage/>}/>
                    <Route path="countries" element={<CountriesPage/>}/>
                    <Route path="countries/:id" element={<CountryDetails/>}/>
                    <Route path="hosts" element={<HostsPage/>}/>
                    <Route path="hosts/:id" element={<HostDetails/>}/>
                    <Route path="accommodations" element={<AccommodationsPage/>}/>
                    <Route path="accommodations/:id" element={<AccommodationDetails/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default App;