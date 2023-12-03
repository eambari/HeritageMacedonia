import React from "react";

const Information = ({label, value}) => {
  return (
    <div>
      <strong>{label}</strong>
      <div>{value ? value : "/"}</div>
    </div>
  );
};

export default Information;
