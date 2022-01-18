import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Home from './home';
import CompanyRegister from './auth/company';
import CitizenRegister from './auth/citizen';
import MerchantRegister from './auth/merchant';
import EmployeeRegister from './auth/employee';
import Login from './auth/login';
import Company from './company';
import Citizen from './citizen';
import Merchant from './merchant';
import Employee from './employee';
import ListCustomers from './merchant/ListCustomers';

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} exact />
          <Route path="/CompanyRegister" element={<CompanyRegister />} />
          <Route path="/CitizenRegister" element={<CitizenRegister />} />
          <Route path="/MerchantRegister" element={<MerchantRegister />} />
          <Route path="/EmployeeRegister" element={<EmployeeRegister />} />
          <Route path="/Login" element={<Login />} />
          <Route path="/Company" element={<Company />} />
          <Route path="/Citizen" element={<Citizen />} />
          <Route path="/Merchant" element={<Merchant />} />
          <Route path="/Employee" element={<Employee />} />
          <Route path="/ListCustomers" element={<ListCustomers />} />
          <Route element={<div>Oops! Page not found!</div>} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
