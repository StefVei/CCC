import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Home from './home';
import CompanyRegister from './register/company';
import CitizenRegister from './register/citizen';

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} exact />
          <Route path="/CompanyRegister" element={<CompanyRegister />} />
          <Route path="/CitizenRegister" element={<CitizenRegister />} />
          <Route element={<div>Oops! Page not found!</div>} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
