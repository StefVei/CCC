import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Home from './home';
import CompanyRegister from './auth/company';
import CitizenRegister from './auth/citizen';
import MerchantRegister from './auth/merchant';
import EmployeeRegister from './auth/employee';
import Login from './auth/login';
import Company from './company';
import CompanyInfo from './company/personalInfo';
import CompanyTransactionHistory from './company/transactionHistory';
import ChooseEmployees from './company/chooseEmployee';
import Citizen from './citizen';
import CitizenInfo from './citizen/personalInfo';
import CitizenTransactionHistory from './citizen/transactionHistory';
import Employee from './employee';
import EmployeeInfo from './employee/personalInfo';
import Merchant from './merchant';
import MerchantTransactionHistory from './merchant/transactionHistory';
import ManageProducts from './merchant/manageProducts';
import ListCustomers from './merchant/ListCustomers';
import BadUsers from './home/badUsers';
import GoodUsers from './home/goodUsers';
import Products from './products';

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
          <Route path="/CompanyInfo" element={<CompanyInfo />} />
          <Route path="/ChooseEmployees" element={<ChooseEmployees />} />
          <Route path="/CompanyTransactionHistory" element={<CompanyTransactionHistory />} />
          <Route path="/Citizen" element={<Citizen />} />
          <Route path="/CitizenInfo" element={<CitizenInfo />} />
          <Route path="/CitizenTransactionHistory" element={<CitizenTransactionHistory />} />
          <Route path="/Employee" element={<Employee />} />
          <Route path="/EmployeeInfo" element={<EmployeeInfo />} />
          <Route path="/Merchant" element={<Merchant />} />
          <Route path="/MerchantTransactionHistory" element={<MerchantTransactionHistory />} />
          <Route path="/ListCustomers" element={<ListCustomers />} />
          <Route path="/ManageProducts" element={<ManageProducts />} />
          <Route path="/Products" element={<Products />} />
          <Route path="/BadUsers" element={<BadUsers />} />
          <Route path="/GoodUsers" element={<GoodUsers />} />
          <Route element={<div>Oops! Page not found!</div>} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
