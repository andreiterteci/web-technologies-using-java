export interface FitnessModel {
  id: string;
  exercises: ExerciseModel[];
  duration: number;
  date: Date;
}

export interface ExerciseModel {
  id: string;
  name: string;
  exerciseType: ExerciseType;
  duration: number;
}

export enum ExerciseType {
  LIFTING = "LIFTING",
  AEROBICS = "AEROBICS",
  YOGA = "YOGA",
  CIRCUIT_TRAINING = "CIRCUIT_TRAINING",
  BICYCLING = "BICYCLING",
  HANDBALL = "HANDBALL",
  SWIMMING = "SWIMMING",
  SOCCER = "SOCCER",
  TENNIS = "TENNIS",
  BASKETBALL = "BASKETBALL",
}
