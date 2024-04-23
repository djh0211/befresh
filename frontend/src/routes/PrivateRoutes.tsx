// PrivateRoute.tsx
import React from 'react';
import { Route, Navigate } from 'react-router-dom';

interface PrivateRouteProps {
  isAuthenticated: boolean;
  redirectTo: string;
}

const PrivateRoute: React.FC<PrivateRouteProps> = ({ isAuthenticated, redirectTo, ...props }) => {
  if (!isAuthenticated) {
    return <Navigate to={redirectTo} />;
  }

  return <Route {...props} />;
};

export default PrivateRoute;
