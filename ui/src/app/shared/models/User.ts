export interface User {
  id: string;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
}

export interface UserLoginData {
  id: string;
  token: string;
  type: string;
  email: string;
  roles: string[];
}
