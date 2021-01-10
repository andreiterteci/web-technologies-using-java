export interface HealthModel {
  healthScore: string;
  healthScoreDecimal: number;
  recommendations: Recommendation[];
}

export enum Recommendation {
  NO_EXERCISE= "It seems like you haven't entered any exercises. You should do some sport to maintain a healthy lifestyle!",
  EXERCISE_NOT_BURN_ENOUGH= "It seems that the exercises you make are not burning enough calories. You might want to try out some different exercises.",
  EXERCISE_BURN_TOO_MUCH = "It seems that the exercises you are making are burning too much calories. Try to take it easier!",
  TOO_MUCH_CALORIES = "It looks like you are consuming too much calories. You should try a diet based on lighter food! (salad never killed anyone)",
  NOT_ENOUGH_CALORIES = "It looks like you are not consuming enough calories. You should try to have 3 meals/day.",
  PERFECTLY_FIT = "You are perfectly fit. Keep it up!"
}
