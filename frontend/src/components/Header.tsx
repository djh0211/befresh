import React from 'react';
import { Link } from 'react-router-dom';

const Header: React.FC = () => {
  return (
    <header>
      <div className="logo">
        <Link to="/">BeFresh</Link>
      </div>
    </header>
  );
};

export default Header;
