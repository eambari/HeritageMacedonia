import React, {useEffect, useState} from "react";
import {useParams} from "react-router";
import Map from "../shared/Map";
import Information from "./Information";
import classes from "./HeritageSite.module.css";
import axios from "axios";
const HeritageSite = (props) => {
  const [heritageData, setHeritageData] = useState({});
  const [showMap, setShowMap] = useState(false);
  const {id} = useParams();
  useEffect(() => {
    const loadHeritage = async () => {
      const data = await axios.get(`https://heritage-macedonia-f654e36c0f1a.herokuapp.com/heritage/get/${id}`);
      setHeritageData(data.data);
      setShowMap(true);
    };
    loadHeritage();
  }, []);
  return (
    <div className={classes.main}>
      <div className={classes.mydata}>
        <h1>{heritageData.name}</h1>
        <div className={`${classes.submain}`}>
          <div>
            <Information label="Address:" value={heritageData.address} />
          </div>
        </div>
      </div>
      <div className={classes.mydata}>
        {showMap ? (
          <Map
            markers={[
              {
                ...heritageData,
                position: {
                  lat: +heritageData.lat,
                  lng: +heritageData.lon,
                },
              },
            ]}
          />
        ) : (
          "Loading..."
        )}
      </div>
    </div>
  );
};

export default HeritageSite;
