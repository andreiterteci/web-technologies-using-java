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

export interface AccountModel {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  gender: Gender;
  weight: number;
  height: number;
  fitnessPlan: FitnessPlan;
  weightGoal: number;
}

export enum Gender {
  MALE = "MALE",
  FEMALE = "FEMALE"
}

export enum FitnessPlan {
  MAINTAIN_WEIGHT = "MAINTAIN_WEIGHT",
  LOSE_WEIGHT = "LOSE_WEIGHT",
  GAIN_WEIGHT = "GAIN_WEIGHT"
}
