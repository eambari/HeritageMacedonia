import './App.css';
import About from "./about/About";
import {Route, Routes} from "react-router-dom";
import DesktopHeader from "./header/DesktopHeader";
import HeritageSite from "./heritage/HeritageSite";
import HeritageContainer from "./heritage/HeritageContainer";
import Introduction from "./intro/Introduction";
function App() {
  return (
    <div className="App">
        <DesktopHeader/>
        <Routes>
            <Route path='/' element={<Introduction/>}/>
            <Route path='/about' element={<About/>}/>
            <Route path="/heritage" element={<HeritageContainer />} />
            <Route path="/heritage/:id" element={<HeritageSite />} />
        </Routes>
    </div>
  );
}

export default App;
