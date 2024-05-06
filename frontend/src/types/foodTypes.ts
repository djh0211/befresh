export interface FoodTypes {
  id: number;
  name: string;
  image: string | null;
  regDttm: string;
  elapsedTime: number;
  refresh: string;
  ftype: string;
  expirationDate: string;
  freshState: number;
  temperature: number | null;
  humidity: number | null;
}

export interface FoodData {
  name: string;
  expirationDate: string;
  regDttm: string;
  elapsedTime: number;
  refresh: string;
  freshState: number; 
}